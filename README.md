# Desafio Backend: Senha Segura 🔒


Desafio proposto pelo repo [Backend Brasil](https://github.com/backend-br/desafios/tree/master) \
Repositorio do desafio [neste link](https://github.com/backend-br/desafios/blob/master/secure-password/PROBLEM.md).

---

## ☕ Tecnologias utilizadas:

- Java 21
- Spring Boot

---

## 💻 Pré-requisitos

Caso queira rodar este projeto na sua própria máquina, veja os requisitos abaixo:

- Java `21`

## 🚀 Instalando

Para instalar o Senha Segura, siga estas etapas:

1. Clone o repositório:
```
https://github.com/igorsily/secure-password.git
```

2. Entre na pasta raiz do projeto
```
cd secure-password
```

3. Execute o projeto com **Maven**
- Maven:
    - ```mvn spring-boot:run```


## 📨 Requisições

| Método | Url                                | Descrição       | Corpo da requisição   |
|--------|------------------------------------|-----------------|-----------------------|
| POST   | /api/v1/security/validate-password | Valide a senha. | [JSON](#validarsenha) |

---

## 📄 Corpo das requisições

##### <a name="validarsenha">/api/v1/security/validate-password - Valide a senha.</a>
#### Retorna 201 se a senha for segura
#### Retorna 400 se a senha for insegura ou não atender aos requisitos

```json 
{
  "password": "AbTp9!fok"
}
```
