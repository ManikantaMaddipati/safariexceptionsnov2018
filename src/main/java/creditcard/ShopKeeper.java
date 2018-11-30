package creditcard;

import com.sun.net.httpserver.Authenticator;

public class ShopKeeper {
  public void sell() throws RetryCCLaterException {
    try {
      new Authorizer().authorizeCC(400);
    } catch (RetryCCLaterException retryEx) {
      throw retryEx;
    } /*
     catch (StolenCardExcepton sce) {}
     catch (NoMoneyException nme ) {}
     */
  }
}
