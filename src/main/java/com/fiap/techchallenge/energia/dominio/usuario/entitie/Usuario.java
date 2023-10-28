package com.fiap.techchallenge.energia.dominio.usuario.entitie;

import com.fiap.techchallenge.energia.dominio.endereco.entitie.Endereco;
import com.fiap.techchallenge.energia.dominio.usuario.dto.response.UsuarioDTO;
import com.fiap.techchallenge.energia.dominio.usuario.dto.response.UsuarioPessoaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    private String senha;
    private LocalDate datanascimento;
    private String sexo;

    @OneToMany(mappedBy = "usuario")
    private List<Endereco> endereco;

    public UsuarioDTO ToUsuarioDTO() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(this.id);
        usuarioDTO.setCpf(this.cpf);
        usuarioDTO.setNome(this.nome);
        usuarioDTO.setTelefone(this.telefone);
        usuarioDTO.setEmail(this.email);
        usuarioDTO.setSenha(this.senha);
        usuarioDTO.setDatanascimento(this.datanascimento);
        usuarioDTO.setSexo(this.sexo);

        return usuarioDTO;
    }

    public UsuarioPessoaDTO ToUsuarioPessoaDTO(){
        return new UsuarioPessoaDTO(this);
    }
}
