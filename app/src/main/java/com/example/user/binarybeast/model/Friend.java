package com.example.user.binarybeast.model;

/**
 * Created by marcus on 2/12/15.
 * @author Marcus Godwin
 * @version 1.0
 *
 */

public class Friend {

    /**
     * Important to note, This is mostly a filler class, nothing much is done with this yet.
     * As we start to get more and more data, we will use this class to interact with friends.
     * Most of the stuff isn't implemented in app yet.
     */
    private String name;
    private String email;
    private int rating;
    private int numReports;

    /**
     * Constructor for Friend class.
     * @param n
     * @param e
     * @param r
     * @param nr
     */
    public Friend(String n, String e, int r, int nr) {
        this.name = n;
        this.email = e;
        this.rating = r;
        this.numReports = nr;
    }

    /**
     * Constructor for Friend class
     * @param n
     * @param e
     */
    public Friend(String n, String e) {
        this(n, e, 0, 0);
    }

    /**
     * Getter for the friend's name.
     * @return the name of the friend.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the friend's email
     * @return the email of the friend.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for the friend's rating
     * @return the friend's rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * Getter for the friend's sales report numbers.
     * @return the number of sales reports.
     */
    public int getNumReports() {
        return numReports;
    }
}
