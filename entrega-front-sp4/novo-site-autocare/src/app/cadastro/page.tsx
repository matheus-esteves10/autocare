"use client";

import axios from "axios";
import { useState } from "react";
import Header from "../../components/Header/Header";
import styles from "./Cadastro.module.css";

export default function Cadastro() {
    const [nome, setNome] = useState("");
    const [cpf, setCpf] = useState("");
    const [email, setEmail] = useState("");
    const [dataNascimento, setDataNascimento] = useState("");
    const [senha, setSenha] = useState("");
    const [confirmaSenha, setConfirmaSenha] = useState("");

    const handleCpfChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        let txtCpf = event.target.value.replace(/\D/g, "");
        txtCpf = txtCpf.replace(/(\d{3})(\d)/, "$1.$2");
        txtCpf = txtCpf.replace(/(\d{3})(\d)/, "$1.$2");
        txtCpf = txtCpf.replace(/(\d{3})(\d{1,2})$/, '$1-$2');
        setCpf(txtCpf);
    };

    const handleDateChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const value = event.target.value.replace(/\D/g, "");
        let formattedValue = "";

        if (value.length <= 2) {
            formattedValue = value;
        } else if (value.length <= 4) {
            formattedValue = value.slice(0, 2) + '/' + value.slice(2);
        } else {
            formattedValue = value.slice(0, 2) + '/' + value.slice(2, 4) + '/' + value.slice(4, 8);
        }

        setDataNascimento(formattedValue);
    };

    function formatarDataParaLista(data: string): number[] {
        const [dia, mes, ano] = data.split("/").map(Number);
        return [ano, mes, dia];
    }    

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        if (senha !== confirmaSenha) {
            alert("As senhas não coincidem.");
            return;
        }

        const cpfFormatado = cpf.replace(/\./g, "").replace(/-/g, "");

        const dataNascimentoFormatadaLista = formatarDataParaLista(dataNascimento);

        const userData = {
            "nome": nome,
            "dataNascimento": dataNascimentoFormatadaLista,
            "cpf": cpfFormatado,
            "email": email,
            "senha": senha
        };

        console.log(userData);

        try {
            const response = await axios.post("http://localhost:8080/autocare_war/api/cadastro", userData);

            if (response.status === 200) {
                alert("Cadastro concluído com sucesso!");
                window.location.href = "/login"; // Redireciona após o cadastro
            }
        } catch (error) {
            if (axios.isAxiosError(error) && error.response) {
                alert(`Erro ao cadastrar: ${error.response.data.message || "Erro desconhecido"}`);
            } else {
                alert("Erro na requisição. Por favor, tente novamente.");
            }
            console.error("Erro:", error);
        }
    };

    return (
        <>
            <Header />
            <div className={styles.container}>
                <div className={styles.boxCadastro}>
                    <h1 className={styles.titulo}>CRIAR CONTA</h1>
                    <form id="formCadastro" onSubmit={handleSubmit}>
                        <div className={styles.divInput}>
                            <input
                                type="text"
                                placeholder="Nome completo"
                                autoComplete="off"
                                autoFocus
                                value={nome}
                                onChange={(e) => setNome(e.target.value)}
                                required
                            />
                        </div>
                        <div className={styles.divInput}>
                            <input
                                type="text"
                                onChange={handleCpfChange}
                                placeholder="CPF"
                                maxLength={14}
                                autoComplete="off"
                                value={cpf}
                                required
                            />
                        </div>
                        <div className={styles.divInput}>
                            <input
                                type="email"
                                placeholder="E-mail"
                                autoComplete="off"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                required
                            />
                        </div>
                        <div className={styles.divInput}>
                            <input
                                type="text"
                                onChange={handleDateChange}
                                placeholder="Data de Nascimento (DD/MM/AAAA)"
                                maxLength={10}
                                autoComplete="off"
                                value={dataNascimento}
                                required
                            />
                        </div>
                        <div className={styles.divInput}>
                            <input
                                type="password"
                                placeholder="Crie uma senha"
                                value={senha}
                                onChange={(e) => setSenha(e.target.value)}
                                required
                            />
                        </div>
                        <div className={styles.divInput}>
                            <input
                                type="password"
                                placeholder="Confirme sua senha"
                                value={confirmaSenha}
                                onChange={(e) => setConfirmaSenha(e.target.value)}
                                required
                            />
                        </div>
                        <button id="formBtn" type="submit">Cadastrar</button>
                        <div className={styles.divisor}></div>
                        <a href="/login" title="Já tenho uma conta" className={styles.link}>
                            Já tenho uma conta
                        </a>
                    </form>
                </div>
            </div>
        </>
    );
}
