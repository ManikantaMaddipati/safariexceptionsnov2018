package creditcard;

import java.net.SocketException;

class ModemDidNotConnectException extends Exception {}
class Modem {
  public void dialModem(int number) throws ModemDidNotConnectException {}
}

// SHOULD be public...
/*public */class RetryCCLaterException extends Exception {
  public RetryCCLaterException() {
  }

  public RetryCCLaterException(String message) {
    super(message);
  }

  public RetryCCLaterException(String message, Throwable cause) {
    super(message, cause);
  }

  public RetryCCLaterException(Throwable cause) {
    super(cause);
  }

  public RetryCCLaterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}

public class Authorizer {
  private boolean USE_MODEM = true;
  public boolean authorizeCC(int amount)
      throws RetryCCLaterException/*, ... */ {
        try {
          if (USE_MODEM) {
            // try a modem
            if (Math.random() > 0.5) throw new ModemDidNotConnectException();
          } else {
            // try a socket
            if (Math.random() > 0.5) throw new SocketException();
          }
        } catch (ModemDidNotConnectException | SocketException ex) {
          throw new RetryCCLaterException("Temporary card auth failure", ex);
        }
    return true;
  }
}
