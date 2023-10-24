package com.fiap.techchallenge.energia.dominio.usuario.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.techchallenge.energia.dominio.usuario.entitie.Usuario;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@ApiModel(description = "Representa um objeto de  Usuários")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDTO {
    @ApiModelProperty(value = "CPF válido do usuario", example = "07506450089", position = 1)
    @CPF(message = "CPF deve ser valido")
    @NotBlank(message = "CPF deve ser preenchido")
    private String cpf;

    @NotBlank(message = "Nome deve ser preenchido")
    @ApiModelProperty(value = "Informacao do Nome do usuario", example = "Zezinho", position = 1)
    private String nome;

    @ApiModelProperty(value = "Informacao do telefone do usuario", example = "11 3258-5303", position = 1)
    @NotBlank(message = "Telefone deve ser preenchido")
    private String telefone;

    @ApiModelProperty(value = "Informacao do email do usuario", example = "xs@hotmail.com", position = 1)
    @NotBlank(message = "email deve ser preenchido")
    private String email;

    @ApiModelProperty(value = "Informacao da senha do usuario", example = "123", position = 1)
    @NotBlank(message = "senha deve ser preenchido")
    private String senha;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Past(message = "Data de nascimento deve ser maior que a Data Atual")
    @ApiModelProperty(value = "Informacao da data de nascimento do usuario", example = "01/01/2000", position = 1)
    private LocalDate datanascimento;

    @ApiModelProperty(value = "Informacao do Sexo do usuario", example = "MASCULINO", position = 1)
    @NotBlank(message = "sexo deve ser preenchido")
    private String sexo;

    @ApiModelProperty(value = "Informacao do parentesco do usuario com o usuario", example = "FILHOS", position = 1)
    @NotBlank(message = "parentesco deve ser preenchido")
    private String parentesco;

    @ApiModelProperty(value = "Código  com o ID do usuario", example = "1", position = 1)
    @NotNull(message = "id do usuario deve ser preenchido")
    private Long idusuario;

    public Usuario toEntity() {
        Usuario usuario = new Usuario();

        usuario.setCpf(this.cpf);
        usuario.setNome(this.nome);
        usuario.setTelefone(this.telefone);
        usuario.setEmail(this.email);
        usuario.setSenha(this.senha);
        usuario.setDatanascimento(this.datanascimento);
        usuario.setSexo(this.sexo);
        usuario.setParentesco(this.parentesco);
        usuario.setIdusuario(this.idusuario);

        return usuario;
    }

    public void ToMapperEntity(Usuario entity) {

        entity.setCpf(this.cpf);
        entity.setNome(this.nome);
        entity.setTelefone(this.telefone);
        entity.setEmail(this.email);
        entity.setSenha(this.senha);
        entity.setDatanascimento(this.datanascimento);
        entity.setSexo(this.sexo);
        entity.setParentesco(this.parentesco);
        entity.setIdusuario(this.idusuario);
    }

}
