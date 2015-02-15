package usercode;

import java.util.concurrent.TimeUnit;

/**
 * Signals that an Application experienced a timeout prior to completing its
 * execution.
 *
 * @author Matthew Mossner
 */
class ApplicationTimeoutException extends RuntimeException {

   /**
    * Constructs an ApplicationTimeoutException with the provided timeout length
    * and unit.
    *
    * @param timeoutLength The amount of time the Application was allowed to
    * run
    * @param timeUnit The unit of time measured
    */
   public ApplicationTimeoutException(long timeoutLength, TimeUnit timeUnit) {
      super("Application timed out after "
            + timeoutLength + " " + timeUnit.name());
   }
}
