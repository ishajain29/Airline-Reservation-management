#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>
#include <unistd.h>
#include <stdlib.h>
#include <my_global.h>
#include <mysql.h>
#define NUM_CHILDREN 20

void error(MYSQL *con)
{
  fprintf(stderr, "%s\n", mysql_error(con));
  mysql_close(con);
  exit(1);
}

char* concat(const char *s1, const char *s2)
{
    char *result = malloc(strlen(s1)+strlen(s2)+1);//+1 for the zero-terminator
    //in real code you would check for errors in malloc here
    strcpy(result, s1);
    strcat(result, s2);
    return result;
}


int main( int argc, char *argv[] )
{
    int sockfd, clisockfd, portno;
    int clilen,i;
     char source[150];
    char dest[100], name[100],dept[100],arr[100],seat[100],fare[100];
    int  n, m, o;
    char *query= NULL;
    pid_t pid;
    
    struct sockaddr_in serv_addr, cli_addr;
    
    //int optval;

    /* First call to socket() function */
    sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd < 0)
    {
        perror("ERROR opening socket");
        return(1);
    }

    /* Initialize socket structure */
    bzero((char *) &serv_addr, sizeof(serv_addr));
    portno = 7000;
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = INADDR_ANY;
    serv_addr.sin_port = htons(portno);


    if (bind(sockfd, (struct sockaddr *) &serv_addr,sizeof(serv_addr)) < 0)
    {
        perror("ERROR on binding");
        return(1);
    }

    listen(sockfd,5);
    
    for (i = 0; i < NUM_CHILDREN; i++) {
		pid = fork();
			
		if (pid == 0) { // We're in the child ...
		    for (;;) { // Run forever ...
			/* Necessary initialization for accept(2) */
			clilen = (socklen_t)sizeof(cli_addr);
			
	
			/* Blocks! */
			clisockfd = accept(sockfd, (struct sockaddr *)&cli_addr, &clilen);

                if (clisockfd < 0)
                {
                    perror("ERROR on accept");
                    return(1);
                }
			puts("\nConnected to Client...");
			close(sockfd);
			/* If connection is established then start communicating */
        n = read( clisockfd,name,99 );
         read( clisockfd,source,149 );
        read( clisockfd,dest,99 );
         read( clisockfd,seat,99 );
        read( clisockfd,fare,99 );
         read( clisockfd,dept,99 );
        read( clisockfd,arr,99 );
    
        //m = read( clisockfd,contentBuffer,254 );
        if (n < 0)
        {
            perror("ERROR reading from socket");
            return(1);
        }
    

                char *val1, *val2;
                printf("%s",name);
                printf("%s",source);
                printf("%s",dest);
                printf("%s",seat);
                printf("%s",fare);
                printf("%s",dept);
                printf("%s",arr);
                int fa = atoi(fare);
                int se = atoi(seat);
                val1 = concat(dept, "00:00:00");
    	        val2 = concat(arr, "00:00:00");
                
                MYSQL *con = mysql_init(NULL);

                 if (con == NULL)
                 {
                     fprintf(stderr, "mysql_init() failed\n");
                     exit(1);
                 }

                 if (mysql_real_connect(con, "localhost", "root", "","airmgmt", 0, NULL, 0) == NULL)
                 {
                     error(con);
                 }
                
                    char statement[512];
                    snprintf(statement, sizeof(statement), "INSERT INTO `airmgmt`.`flight` (`name`, `source`, `destination`, `no_of_seats`, `fare`, `departure`, `arrival`) VALUES ('%s', '%s', '%s', '%d', '%d', '%s', '%s');",name , source, dest, se, fa, val1,val2);
                 if (mysql_query(con,statement))
                 {
                     error(con);
                 }
                 MYSQL_RES *result = mysql_store_result(con);

                 if (result == NULL)
                 {
                     error(con);
                 }
                 int fields = mysql_num_fields(result);
                 printf("%d\n", fields);

			close(clisockfd);
		    }
		}
    	}
    	/* Sit back and wait for all child processes to exit */
    	while (waitpid(-1, NULL, 0) > 0);

	return 0;
           
}
