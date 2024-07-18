package com.mytree.mytree.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Link {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "redirect_link")
    private String redirectLink;

    @ManyToOne
    @JoinColumn(name = "page_id")
    private Page page;

    public Link(String title, String redirectLink, Page page) {
        this.title = title;
        this.redirectLink = redirectLink;
        this.page = page;
    }
}
