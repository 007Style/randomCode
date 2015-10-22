package com.midnightcookies.taghandlers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.IterationTag;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *  Generated tag class.
 */
public class ContentHandler extends TagSupport {
    
    
    public ContentHandler() {
        super();
    }
    
    ////////////////////////////////////////////////////////////////
    ///                                                          ///
    ///   User methods.                                          ///
    ///                                                          ///
    ///   Modify these methods to customize your tag handler.    ///
    ///                                                          ///
    ////////////////////////////////////////////////////////////////
    
    /**
     *
     * otherDoStartTagOperations includes the file specified
     * in the request scope page attributes, or if that fails,
     * includes nonesuch.jsp.
     */
    public void otherDoStartTagOperations()  {
        
        ServletRequest request = pageContext.getRequest();
        ServletResponse response = pageContext.getResponse();
        
        String page = (String)request.getAttribute("page");
        
        try {
            request.getRequestDispatcher(page).include(request, response);
        }
        catch(Throwable t) {
            System.out.println("Selected file does not exist");
            request.setAttribute("nonesuch", page);
            try {
                request.getRequestDispatcher("/WEB-INF/docs/nonesuch.jsp").include(request, response);
            }
            catch(ServletException ex) {
                ex.printStackTrace();
                // do nothing, we know this file exists
            }
            catch(IOException ex) {
                ex.printStackTrace();
                // do nothing, we know this file exists
            }
        }
    }
    
    /**
     *
     * Fill in this method to determine if the tag body should be evaluated
     * Called from doStartTag().
     *
     * Return false, since this tag is declared to have an empty body.
     */
    public boolean theBodyShouldBeEvaluated()  {
        return false;
    }
    
    /**
     *
     * doEndTag() does nothing
     *
     */
    public void otherDoEndTagOperations()  {
        return;
    }
    
    /**
     *
     * The rest of the JSP page should be evaluated after this tag
     * is finished..
     *
     */
    public boolean shouldEvaluateRestOfPageAfterEndTag()  {
        return true;
    }
    
    
    ////////////////////////////////////////////////////////////////
    ///                                                          ///
    ///   Tag Handler interface methods.                         ///
    ///                                                          ///
    ///   Do not modify these methods; instead, modify the       ///
    ///   methods that they call.                                ///
    ///                                                          ///
    ////////////////////////////////////////////////////////////////
    
    
    /** .
     *
     * This method is called when the JSP engine encounters the start tag,
     * after the attributes are processed.
     * Scripting variables (if any) have their values set here.
     * @return EVAL_BODY_INCLUDE if the JSP engine should evaluate the tag body, otherwise return SKIP_BODY.
     * This method is automatically generated. Do not modify this method.
     * Instead, modify the methods that this method calls.
     *
     *
     */
    public int doStartTag() throws JspException, JspException {
        otherDoStartTagOperations();
        
        if (theBodyShouldBeEvaluated()) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }
    
    /** .
     *
     *
     * This method is called after the JSP engine finished processing the tag.
     * @return EVAL_PAGE if the JSP engine should continue evaluating the JSP page, otherwise return SKIP_PAGE.
     * This method is automatically generated. Do not modify this method.
     * Instead, modify the methods that this method calls.
     *
     *
     */
    public int doEndTag() throws JspException, JspException {
        otherDoEndTagOperations();
        
        if (shouldEvaluateRestOfPageAfterEndTag()) {
            return EVAL_PAGE;
        } else {
            return SKIP_PAGE;
        }
    }
}
