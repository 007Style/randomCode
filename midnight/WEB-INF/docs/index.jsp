<%@page contentType="text/html"%>

<p>
This application illustrates some basic features of web application
architecture, such as the use of a Servlet front controller, and using
a composite view within a JSP page. See 
<a href="<%=request.getContextPath()%>/docs/about/about.jsp">here</a>
for an outline of this application's architecture.  
</p>

<p>
The JSP pages use JSTL, instead of scripting, to fetch dynamic data,
and to internationalize the pages. For examples of how this is done,
check the <tt>/WEB-INF/docs/cookies/Tray.jsp</tt> or
<tt>/WEB-INF/docs/nonesuch.jsp</tt> source files.  
</p>

<p>
If you want to follow the data flow through the forwarded and included
resources as they are executed, it is recommended that you use the
HTTP monitor using the instructions given in this mini-tutorial.
</p>
