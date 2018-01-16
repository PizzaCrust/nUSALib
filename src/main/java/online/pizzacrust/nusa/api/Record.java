package online.pizzacrust.nusa.api;

public interface Record {

    /**
     * Is the specified user in the record, is something in the record?
     * @return
     */
    boolean is();

    /**
     * Retrieves the ID of the user in the record
     * @return
     */
    int getId();

}
