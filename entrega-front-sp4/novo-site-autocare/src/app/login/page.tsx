"use client";

import axios from "axios";
import Header from "../../components/Header/Header";
import styles from "./Login.module.css";
import { useState } from "react";


export default function Login() {
    const [login, setLogin] = useState('');
    const [senha, setSenha] = useState('');
    const [error, setError] = useState(false);


    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        const dadosLogin = {
            login: login, // CPF ou E-mail
            senha: senha, // Senha
        };

        try {
            const response = await axios.post("http://localhost:8080/autocare_war/api/login", dadosLogin);

            // Verifica se a resposta foi bem-sucedida
            if (response.status == 200 && response.data) { // Supondo que a resposta contenha o objeto do usuário
                sessionStorage.setItem("idUsuario", response.data[0].id);
                alert(`Olá, ${response.data[0].nome}!`); // Exibe o nome do usuário
                window.location.href = "/chatbot"; // Redireciona para a página do chatbot
            } else if (response.status == 404) {
                setError(true); // Se não for sucesso, exibe erro
                setSenha(''); // Limpa a senha
            }
        } catch (error) {
            console.error("Erro ao fazer login: ", error);
            setError(true); // Exibe erro em caso de falha na requisição
            setSenha(''); // Limpa a senha
        }
    };

    return (
        <>
            <Header />
            <div className={styles.container}>
                <div className={`${styles.boxLogin} ${error ? styles.erro : ''}`}>
                    <h2 className={styles.titulo}>LOGIN</h2>
                    {error && <p>Login e/ou Senha inválido(s).</p>}
                    <form id={styles.formLogin} onSubmit={handleSubmit}>
                        <input
                            type="text"
                            value={login}
                            onChange={(e) => setLogin(e.target.value)}
                            placeholder="CPF ou E-mail"
                            autoComplete="off"
                            autoFocus
                            required
                        />
                        <input
                            type="password"
                            value={senha}
                            onChange={(e) => setSenha(e.target.value)}
                            placeholder="Senha"
                            required
                        />
                        <button type="submit" id="form-btn">Entrar</button>
                        <div className={styles.divisor}></div>
                        <a href="/cadastro" title="Criar uma conta">Criar uma conta</a> {/* Usando tag <a> para navegação */}
                    </form>
                </div>
            </div>
        </>
    );
}
