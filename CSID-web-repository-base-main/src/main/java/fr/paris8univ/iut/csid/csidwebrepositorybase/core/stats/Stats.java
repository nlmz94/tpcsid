package fr.paris8univ.iut.csid.csidwebrepositorybase.core.stats;

public class Stats {

    private String repository_name;
    private String entry_type;
    private String date_time;
    private Integer value;

    public Stats(String repository_name,String entry_type, String date_time, Integer value) {

        this.repository_name = repository_name;
        this.entry_type = entry_type;
        this.date_time = date_time;
        this.value = value;
    }

    public Stats() {}

    public String getRepository_name() { return repository_name; }
    public void setRepository_name(String repository_name) { this.repository_name = repository_name; }
    public String getEntry_type() { return entry_type; }
    public void setEntry_type(String entry_type) { this.entry_type = entry_type; }
    public String getDate_time() { return date_time; }
    public void setDate_time(String date_time) { this.date_time = date_time; }
    public Integer getValue() { return value; }
    public void setValue(Integer value) { this.value = value; }
}
