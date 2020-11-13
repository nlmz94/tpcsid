package fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain;

public class GitRepository {

    private final String name;
    private final String owner;

    public GitRepository(String name, String owner) {
        this.name =name;
        this.owner = owner;
    }
    public String getName() {
        return this.name;
    }
    public String getOwner() {
        return this.owner;
    }
}
