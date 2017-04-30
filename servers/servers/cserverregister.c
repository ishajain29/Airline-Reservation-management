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
     char usrbuff[150];
    char passbuff[100], name[100],address[100],mobile[100],passport[100];
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
    portno = 6050;
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
         read( clisockfd,usrbuff,149 );
        read( clisockfd,passbuff,99 );
         read( clisockfd,passport,99 );
        read( clisockfd,address,99 );
         read( clisockfd,mobile,99 );
    
        //m = read( clisockfd,contentBuffer,254 );
        if (n < 0)
        {
            perror("ERROR reading from socket");
            return(1);
        }
    

        
                printf("%s",usrbuff);
                printf("%s",passbuff);
                printf("%s",name);
                printf("%s",address);
                printf("%s",mobile);
                printf("%s",passport);
                int mob = atoi(mobile);
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
                    snprintf(statement, sizeof(statement), "INSERT INTO `airmgmt`.`user` (`name`, `password`, `email`, `address`, `mobile`, `usertype_id`, `passport`) VALUES ('%s', '%s', '%s', '%s', '%d', '3', '%s')",name , passbuff, usrbuff, address, mob, passport);
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
