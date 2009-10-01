package sendmail.ui;

/**
 * This interface defines the various constants used in this application,
 * including forwards and various kinds of keys.
 */
public interface Constants {

  String FORWARD_SUCCESS = "success";
  String FORWARD_FAILURE = "failure";
  String FORWARD_RESTART = "restart";

  String PARAMETER_TO      = "to";
  String PARAMETER_FROM    = "from";
  String PARAMETER_SUBJECT = "subject";
  String PARAMETER_MESSAGE = "message";
  
  String ATTRIBUTE_MESSAGE_KEY = "messageKey";

  String BEAN_MAILSENDER = "mailSender";
}