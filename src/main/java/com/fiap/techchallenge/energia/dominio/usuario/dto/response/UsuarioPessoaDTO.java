package com.fiap.techchallenge.energia.dominio.usuario.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.techchallenge.energia.dominio.endereco.dto.response.EnderecoDTO;
import com.fiap.techchallenge.energia.dominio.pessoa.dto.response.PessoaDTO;
import com.fiap.techchallenge.energia.dominio.usuario.entitie.Usuario;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPessoaDTO {

    private Long id;
    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    private String senha;
    private LocalDate datanascimento;
    private String sexo;
    private List<PessoaDTO> pessoa = new ArrayList<>();
    private List<EnderecoDTO> endereco = new ArrayList<>();

    public UsuarioPessoaDTO(Usuario usuario)
    {
        this.id = usuario.getId();
        this.cpf = usuario.getCpf();
        this.nome = usuario.getNome();
        this.telefone = usuario.getTelefone();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.datanascimento = usuario.getDatanascimento();
        this.sexo = usuario.getSenha();
        usuario.getPessoa().forEach( pessoa -> this.pessoa.add(new PessoaDTO(pessoa)));
        usuario.getEndereco().forEach( endereco -> this.endereco.add(new EnderecoDTO(endereco)));
    }

}
