package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;
import javax.persistence.*;

@Entity
@Table(name = "repository")
class GitRepositoryEntity {

    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "owner")
    private String owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
