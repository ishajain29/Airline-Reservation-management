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

void login(int s){
    char buffer[256];
    char contentBuffer[255];
    int  n, m;
    char *query= NULL;
    bzero(buffer,256);
    bzero(contentBuffer,255);
    /* If connection is established then start communicating */
    n = read( s,buffer,255 );
    m = read( s,contentBuffer,254 );
    if (n < 0 || m < 0)
    {
        perror("ERROR reading from socket");
        return;
    }

    printf("%s",buffer);
    printf("%s",contentBuffer);

    {
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

         if (mysql_query(con,"SELECT email, password FROM user" ))
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
         char *val1, *val2;
        int flag = 0;

         while ((row = mysql_fetch_row(result)))
         {
    	     val1 = concat(row[0], "\n");
    	     val2 = concat(row[1], "\n");

        	 if(strcmp(buffer,val1) == 0 && strcmp(contentBuffer,val2) == 0){
                    		   //printf("%s\n%s",row[0],row[1]);
                    		   //n = write(clisockfd,"Invalid Login Id or password",28);
                n = write(s,"Login Successful",16);
                 flag = 1;
                break;
             }
          }
        if(flag == 0){
            n = write(s,"Invalid Login Id or password",28);
        }
          mysql_close(con);
    }
}



int main( int argc, char *argv[] )
{
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
    portno = 6000;
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
			login(clisockfd);
			close(clisockfd);
		    }
		}
    	}
    	/* Sit back and wait for all child processes to exit */
    	while (waitpid(-1, NULL, 0) > 0);

	return 0;
        
}
