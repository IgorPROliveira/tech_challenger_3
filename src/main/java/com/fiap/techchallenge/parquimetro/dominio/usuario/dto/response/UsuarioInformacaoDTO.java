package com.fiap.techchallenge.parquimetro.dominio.usuario.dto.response;

import com.fiap.techchallenge.parquimetro.dominio.endereco.dto.response.EnderecoDTO;
import com.fiap.techchallenge.parquimetro.dominio.estacionamento.dto.response.EstacionamentoDTO;
import com.fiap.techchallenge.parquimetro.dominio.pagamento.dto.response.PagamentoDTO;
import com.fiap.techchallenge.parquimetro.dominio.usuario.entitie.Usuario;
import com.fiap.techchallenge.parquimetro.dominio.veiculo.dto.response.VeiculoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioInformacaoDTO {

    private Long id;
    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    private String senha;
    private LocalDate datanascimento;
    private String sexo;
    private List<EnderecoDTO> endereco = new ArrayList<>();
    private List<PagamentoDTO> pagamentos = new ArrayList<>();
    private List<VeiculoDTO> veiculos = new ArrayList<>();
    private List<EstacionamentoDTO> estacionamentos = new ArrayList<>();


    public UsuarioInformacaoDTO(Usuario usuario)
    {
        this.id = usuario.getId();
        this.cpf = usuario.getCpf();
        this.nome = usuario.getNome();
        this.telefone = usuario.getTelefone();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.datanascimento = usuario.getDatanascimento();
        this.sexo = usuario.getSexo();
        usuario.getEndereco().forEach( endereco -> this.endereco.add(new EnderecoDTO(endereco)));
        usuario.getPagamentos().forEach(pagamento -> this.pagamentos.add(new PagamentoDTO(pagamento)));
        usuario.getVeiculos().forEach(veiculo -> this.veiculos.add(new VeiculoDTO(veiculo)));
        usuario.getEstacionamentos().forEach(estacionamento -> this.estacionamentos.add(new EstacionamentoDTO(estacionamento)));
    }

}
