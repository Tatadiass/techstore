package br.com.t2_fat.techstore.repository;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "pedidos")

public class pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "clientes_id")
    private clientes clientes;

    @OneToMany(mappedBy = "pedidos", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public clientes getClientes() {
        return clientes;
    }

    public void setClientes(clientes clientes) {
        this.clientes = clientes;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

}
