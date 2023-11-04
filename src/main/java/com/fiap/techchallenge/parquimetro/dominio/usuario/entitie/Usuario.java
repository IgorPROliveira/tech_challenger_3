package com.fiap.techchallenge.parquimetro.dominio.usuario.entitie;

import com.fiap.techchallenge.parquimetro.dominio.endereco.entitie.Endereco;
import com.fiap.techchallenge.parquimetro.dominio.estacionamento.entitie.Estacionamento;
import com.fiap.techchallenge.parquimetro.dominio.pagamento.entitie.Pagamento;
import com.fiap.techchallenge.parquimetro.dominio.usuario.dto.response.UsuarioDTO;
import com.fiap.techchallenge.parquimetro.dominio.usuario.dto.response.UsuarioInformacaoDTO;
import com.fiap.techchallenge.parquimetro.dominio.veiculo.entitie.Veiculo;
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
    @OneToMany(mappedBy = "usuario")
    private List<Pagamento> pagamentos;
    @OneToMany(mappedBy = "usuario")
    private List<Veiculo> veiculos;
    @OneToMany(mappedBy = "usuario")
    private List<Estacionamento> estacionamentos;



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

    public UsuarioInformacaoDTO ToUsuarioInformacaoDTO(){
        return new UsuarioInformacaoDTO(this);
    }

}
