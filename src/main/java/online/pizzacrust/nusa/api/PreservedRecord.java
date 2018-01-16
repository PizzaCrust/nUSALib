package online.pizzacrust.nusa.api;

/**
 * Represents a Record but records of the past are recorded.
 */
public interface PreservedRecord extends Record {

    boolean was();

}
