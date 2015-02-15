package businessobjects;

import java.time.LocalDateTime;

/**
 * User object for authentication and identity.
 *
 * @author Jacob Gorney
 *
 */
public class User {

    /**
     * The creation time of this user object.
     */
    private LocalDateTime createdAt = LocalDateTime.now();
    /**
     * User ID of the user. This is retrieved from DBMgr.
     */
    protected int userId;
    /**
     * Username of the user. This is retrieved from DBMgr.
     */
    protected String username;
    /**
     * Username of the user. This is retrieved from DBMgr.
     */
    protected String password;

    /**
     * Constructor to build a user that has already been authenticated or the
     * identity is already known.
     *
     * @param username Username
     * @param password Password
     */
    protected User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
