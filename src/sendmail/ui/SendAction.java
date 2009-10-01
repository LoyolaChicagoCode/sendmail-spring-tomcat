package sendmail.ui;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SendAction extends Action implements Constants {
    
    private MailSender mailSender;
    
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
    
    protected MailSender getMailSender() {
        return mailSender;
    }

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) throws Exception {

		// obtain arguments from form bean
		DynaActionForm mailForm = (DynaActionForm) form;
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom((String) mailForm.get(PARAMETER_FROM));
        msg.setTo((String) mailForm.get(PARAMETER_TO));
        msg.setSubject((String) mailForm.get(PARAMETER_SUBJECT));
        msg.setText((String) mailForm.get(PARAMETER_MESSAGE));

        // do the work

        try{
          getMailSender().send(msg);
          System.out.println("Message sent to " + msg.getTo()[0]);
          request.setAttribute(ATTRIBUTE_MESSAGE_KEY, "mail.sent");
        }
        catch(MailException ex) {
            System.err.println(ex.getMessage());            
            request.setAttribute(ATTRIBUTE_MESSAGE_KEY, "mail.failed");
        }
	    
		// choose forwarding target
		return mapping.findForward(FORWARD_SUCCESS);
	}
}