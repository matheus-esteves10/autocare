"use client";

import axios from "axios";
import styles from "./DialogConfirmacao.module.css";
import { DialogConfirmacaoProps } from "../../interfaces";
import { useRef, useEffect, useState } from "react";
import { useSessionStorage } from "../../hooks/useSessionStorage";

export default function DialogConfirmacao({ isOpen, onClose, problemaVeiculo, orcamento }: DialogConfirmacaoProps) {
    const dialogRef = useRef<HTMLDialogElement>(null);
    const [foiConfirmado, setFoiConfirmado] = useState<boolean>(false);

    // Manipulando open e close
    useEffect(() => {
        if (isOpen && dialogRef.current) {
            dialogRef.current.showModal();
        } else if (dialogRef.current) {
            dialogRef.current.close();
        }
    }, [isOpen]);

    // Pegando info do agendamento
    const idUsuario = useSessionStorage("idUsuario");
    const idOficina = useSessionStorage("idOficinaSelecionada");
    const nomeOficina = useSessionStorage("nomeOficinaSelecionada");
    const dia = useSessionStorage("diaNumSelecionado");
    const mes = useSessionStorage("mesSelecionado");
    const diaSemana = useSessionStorage("diaSemanaSelecionado");
    const horario = useSessionStorage("horarioSelecionado");

    const mesParaNumero = (mes: string): number | null => {
        const meses: { [key: string]: number } = {
            'JAN': 1,
            'FEV': 2,
            'MAR': 3,
            'ABR': 4,
            'MAI': 5,
            'JUN': 6,
            'JUL': 7,
            'AGO': 8,
            'SET': 9,
            'OUT': 10,
            'NOV': 11,
            'DEZ': 12
        };
        return meses[mes.toUpperCase()] || null; // Converte para maiúsculas e busca
    };

    const mesNumero = mes ? mesParaNumero(mes) : null;

    const handleClickConfirmar = async () => {
        if (!idOficina || !dia || !mesNumero || !horario) {
            alert("Informações insuficientes para confirmar o agendamento.");
            return;
        }

        const dataAgendamento = [2024, mesNumero, dia];
        const agendamento = {
            "dataAgendamento": dataAgendamento,
            "horaDoAgendamento": horario,
            "descricaoServico": problemaVeiculo,
            "idOficina": idOficina,
            "idUsuario": idUsuario
        };

        try {
            const response = await axios.post("http://localhost:8080/autocare_war/api/agendamento", agendamento);

            if (response.status === 200) {
                setFoiConfirmado(true); // Exibe mensagem de confirmação
            }
        } catch (error) {
            if (axios.isAxiosError(error) && error.response) {
                alert(`Erro ao confirmar agendamento: ${error.response.data.message || "Erro desconhecido"}`);
            } else {
                alert("Erro na requisição. Por favor, tente novamente.");
            }
            console.error("Erro:", error);
        }
    };

    const handleVoltarParaChatbot = () => {
        window.location.href = '/chatbot'; // Redireciona para a página do chatbot
    };

    return (
        <dialog ref={dialogRef} className={styles.dialogConfirmacao}>
            {foiConfirmado === false ? (
                <div className={styles.container}>
                    <div className={styles.infoAgendamento}>
                        <h1>Confirme seu agendamento</h1>
                        <p>{dia}/{mesNumero}/2024 | {diaSemana} - {horario}</p>
                        <p>{nomeOficina}</p>
                        <p>Problema: {problemaVeiculo}</p>
                        <p>Orçamento aproximado: R$ {orcamento}</p>
                    </div>

                    <div className={styles.divBotoes}>
                        <button className={styles.cancelar} onClick={onClose}>Cancelar</button>
                        <button className={styles.confirmar} onClick={handleClickConfirmar}>Confirmar</button>
                    </div>
                </div>
            ) : (
                <div className={styles.divConfirmado}>
                    <h1>Agendamento Confirmado!</h1>
                    <p>Estamos aqui sempre que precisar. Boa sorte!</p>
                    <button onClick={handleVoltarParaChatbot} className={styles.voltar}>Voltar para Chatbot</button>
                </div>
            )}
        </dialog>
    );
}
