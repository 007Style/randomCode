Below is a description of the contents of the Midnight Cookie Company application:
<font size="-1">
<pre>
midnight
    WEB-INF
        Classes 
            com
                midnightcookies
                    FrontController:  The applications front controller.
                                      All requests are routed through 
                                      this servlet. 
		    Midnight.properties: stores the messages. 

                    taghandlers: 
                        ButtonHandler: Prints a submit button with a 
                                       localized message
		        ContentHandler: Handles the content inclusion 
					from the template page
                        Linkshandler:  Prints a set of links with a 
                                       separator from the tag attribute
         docs: This directory contains all the JSP pages used by the
	       application. The application uses a pattern called
	       Composite View or template. 
	       
               After the front controller has finished, all requests
	       are passed to the JSP page called main.jsp. The
	       main.jsp uses dynamic includes and the LinksHandler
	       class to build up the page.  

        lib: Contains JSTL JAR files. This application uses the
              core and format tag libraries from the JSTL.
              Core is used to set variables and for conditional statements,
	      and format is used to provide localized messages.

        tlds: Contains the tag library descriptor for the midnight
              tag library.

         web.xml: This is the deployment descriptor file. 

</pre>
</font>
