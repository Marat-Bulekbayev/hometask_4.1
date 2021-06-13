package util;

public class ErrorMessageGenerator {

    private static final String FAILED_TEST_NAME = "Failed test name: ";

    private ErrorMessageGenerator(){}

    public static String generateErrorMessage(String methodName) {
        switch (methodName) {
            case "testLoginToMailbox" :
                return FAILED_TEST_NAME + methodName + ". True - \"write mail\" element is displayed, false is otherwise";
            case "testNewMailPresenceInDrafts" :
                return FAILED_TEST_NAME + methodName + ". True - draft mail is present in folder, false is otherwise";
            case "testDraftMailContent" :
                return FAILED_TEST_NAME + methodName + ". True - draft mail is verified in folder, false is otherwise";
            case "testSentMailAbsenceInDraftFolder" :
                return FAILED_TEST_NAME + methodName + ". False - sent mail is absent in Draft folder, true is otherwise";
            case "testSentMailPresenceInSentFolder" :
                return FAILED_TEST_NAME + methodName + ". True - sent mail is verified in Sent folder, false is otherwise";
            default :
                return "Condition is failed.";
        }
    }
}
