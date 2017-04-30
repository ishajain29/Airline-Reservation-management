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

void strip(char *s) {
    char *p2 = s;
    while(*s != '\0') {
        if(*s != '\t' && *s != '\n') {
            *p2++ = *s++;
        } else {
            ++s;
        }
    }
    *p2 = '\0';
}




int main( int argc, char *argv[] )
{
    char from[256];
    char to[255],depart[100];
    int  len0,len1,len2,len3;
    char *query= NULL;
    int sockfd, clisockfd, portno,i;
    int clilen;
    struct sockaddr_in serv_addr, cli_addr;
    int  n, m;
    
    pid_t pid;
    
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
    portno = 6070;
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = INADDR_ANY;
    serv_addr.sin_port = htons(portno);


    if (bind(sockfd, (struct sockaddr *) &serv_addr,sizeof(serv_addr)) < 0)
    {
        perror("ERROR on binding");
        return(1);
    }

    listen(sockfd,15);
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
			n = read( clisockfd,from,255 );
            m = read( clisockfd,to,254 );
            read(clisockfd,depart,99);
            
    if (n < 0 || m < 0)
    {
        perror("ERROR reading from socket");
        return(1);
    }

    printf("%s",from);
    printf("%s",to);
    printf("%s",depart);
    
        strip(from);
        strip(to);
        strip(depart);
        
    
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
         snprintf(statement, sizeof(statement), "SELECT name,source,destination,fare,no_of_seats FROM airmgmt.flight where departure = '%s'and source = '%s' and destination = '%s';",depart,from,to);
                 printf("after query");
         if (mysql_query(con,statement ))
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

         MYSQL_ROW row;
         char a[20]="Hello";

         while ((row = mysql_fetch_row(result)))
         {
    	       for(i=0;i<fields;i++){
                    printf("%s\t",row[i]);
                   write(clisockfd,row[i],strlen(row[i]));
               } 
             printf("\n");
          }
        
          mysql_close(con);
    
			close(clisockfd);
		    }
		}
    	}
    	/* Sit back and wait for all child processes to exit */
    	while (waitpid(-1, NULL, 0) > 0);

	return 0;
        
}
