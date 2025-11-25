package ca.humber.sanaullah.cpan228finalprojecttuansanaullah.model;

import jakarta.persistence.*;

@Entity
@Table(name = "secondary_dish")
public class SecondaryDish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Double price;

    @Column
    private String sourceService;

    // No-args constructor
    public SecondaryDish() {
    }

    // Constructor
    public SecondaryDish(String name, String category, Double price, String sourceService) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.sourceService = sourceService;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSourceService() {
        return sourceService;
    }

    public void setSourceService(String sourceService) {
        this.sourceService = sourceService;
    }
}