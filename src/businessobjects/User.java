/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Jacob Gorney, Max Savard, Matt Mossner, Spencer Kokaly
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
