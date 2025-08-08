package com.RestAPIFurb.WEB2.Entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_comandas")
public class Comanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Double preco;
    private LocalDate data;
    @NotNull
    @ManyToOne
    private Usuario usuario;
    @NotNull
    @OneToMany(mappedBy = "comanda", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ItemComanda> itens;

    @Override
    public String toString() {
        String listaProdutos = itens.stream()
                .map(item -> item.getProduto().getNomeProduto())
                .reduce((a, b) -> a + ", " + b)
                .orElse("Nenhum produto");

        return """
                Responsável: %s
                Telefone: %s
                Produto(s) solicitados: %s
                """.formatted(usuario.getNome(), usuario.getTelefone(), listaProdutos);
    }
}
