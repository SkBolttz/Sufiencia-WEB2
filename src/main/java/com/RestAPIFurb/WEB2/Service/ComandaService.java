package com.RestAPIFurb.WEB2.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.RestAPIFurb.WEB2.DTO.ComandaDTO;
import com.RestAPIFurb.WEB2.DTO.ProdutoDTO;
import com.RestAPIFurb.WEB2.Entity.Comanda;
import com.RestAPIFurb.WEB2.Entity.ItemComanda;
import com.RestAPIFurb.WEB2.Entity.Produto;
import com.RestAPIFurb.WEB2.Entity.Usuario;
import com.RestAPIFurb.WEB2.Exception.Comanda.ComandaNaoLocalizadaException;
import com.RestAPIFurb.WEB2.Exception.Produto.ProdutoNaoLocalizadoException;
import com.RestAPIFurb.WEB2.Repository.ComandaRepository;
import com.RestAPIFurb.WEB2.Repository.ProdutoRepository;
import com.RestAPIFurb.WEB2.Repository.UsuarioRepository;

@Service
public class ComandaService {

    private final ComandaRepository comandaRepository;
    private final UsuarioRepository userRepository;
    private final ProdutoRepository produtoRepository;

    public ComandaService(ComandaRepository comandaRepository, UsuarioRepository userRepository,
            ProdutoRepository produtoRepository) {
        this.comandaRepository = comandaRepository;
        this.userRepository = userRepository;
        this.produtoRepository = produtoRepository;
    }

    public void registrarComanda(ComandaDTO comandaDTO) {
        Usuario usuario = userRepository.findByNome(comandaDTO.nomeResponsavel());

        if (usuario == null) {
            Usuario usuarioEntity = new Usuario();
            usuarioEntity.setNome(comandaDTO.nomeResponsavel());
            usuarioEntity.setTelefone(comandaDTO.telefoneResponsavel());
            usuario = userRepository.save(usuarioEntity);
        }

        Comanda comandaEntity = new Comanda();
        comandaEntity.setUsuario(usuario);
        comandaEntity.setData(LocalDate.now());

        double somatoriaComandas = 0;
        List<ItemComanda> produtosRelacionados = new ArrayList<>();

        for (ProdutoDTO produtoDTO : comandaDTO.produtos()) {
            Produto produto = produtoRepository.findByNomeProduto(produtoDTO.nomeProduto())
                    .orElseThrow(
                            () -> new ProdutoNaoLocalizadoException("Erro ao localizar produto",
                                    "Produto não encontrado com id: " + produtoDTO.nomeProduto()));

            somatoriaComandas += produtoDTO.precoProduto() * produtoDTO.quantidade();

            ItemComanda itemComanda = new ItemComanda();
            itemComanda.setProduto(produto);
            itemComanda.setQuantidade(produtoDTO.quantidade());
            itemComanda.setComanda(comandaEntity);

            produtosRelacionados.add(itemComanda);
        }

        comandaEntity.setPreco(somatoriaComandas);
        comandaEntity.setItens(produtosRelacionados);
        comandaRepository.save(comandaEntity);
    }

    public List<Comanda> listarComandas() {
        return comandaRepository.findAll();
    }

    public Comanda listarComandaPorId(Long id) {
        return comandaRepository.findById(id)
                .orElseThrow(
                        () -> new ComandaNaoLocalizadaException("Erro ao localizar comanda", "Comanda não encontrada"));
    }

    public void editarComanda(Long id, ComandaDTO comanda) {

        Comanda comandaEntity = comandaRepository.findById(id)
                .orElseThrow(
                        () -> new ComandaNaoLocalizadaException("Erro ao localizar comanda", "Comanda não encontrada"));

        if (comanda.nomeResponsavel() != null) {
            comandaEntity.getUsuario().setNome(comanda.nomeResponsavel());
        }

        if (comanda.telefoneResponsavel() != null) {
            comandaEntity.getUsuario().setTelefone(comanda.telefoneResponsavel());
        }

        if (comanda.produtos() != null) {
            double novoTotal = 0;

            for (ProdutoDTO produtoDTO : comanda.produtos()) {
                ItemComanda itemExistente = comandaEntity.getItens().stream()
                        .filter(item -> item.getProduto().getNomeProduto().equals(produtoDTO.nomeProduto()))
                        .findFirst()
                        .orElseThrow(() -> new ProdutoNaoLocalizadoException(
                                "Erro ao localizar produto",
                                "Produto não encontrado na comanda: " + produtoDTO.nomeProduto()));

                if (produtoDTO.quantidade() > 0) {
                    itemExistente.setQuantidade(produtoDTO.quantidade());
                } else {
                    comandaEntity.getItens().remove(itemExistente);
                    continue;
                }
            }

            novoTotal = comandaEntity.getItens().stream()
                    .mapToDouble(item -> item.getProduto().getPrecoProduto() * item.getQuantidade())
                    .sum();

            comandaEntity.setPreco(novoTotal);
        }
        comandaRepository.save(comandaEntity);
    }

    public void deletarComanda(Long id) {

        Comanda comandaEntity = comandaRepository.findById(id)
                .orElseThrow(
                        () -> new ComandaNaoLocalizadaException("Erro ao localizar comanda", "Comanda nao encontrada"));

        comandaRepository.delete(comandaEntity);
    }
}
