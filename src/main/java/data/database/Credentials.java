package data.database;

/**
 * Abstract class that allows establishing connection to any PostgreSQL database.
 * */
public abstract class Credentials {
    private final String DB;
    private final String OWNER;
    private final String PASSWORD;
    private final String URL;

    public Credentials(String db, String owner, String pwd) {
        this.DB = db;
        this.OWNER = owner;
        this.PASSWORD = pwd;
        this.URL = "jdbc:postgresql://localhost:5432/" + db;
    }

    public String getDB() {
        return this.DB;
    }

    public String getOwner() {
        return this.OWNER;
    }

    public String getPassword() {
        return this.PASSWORD;
    }

    public String getUrl() {
        return this.URL;
    }

    abstract void setConnection();

    abstract void disconnect();
}