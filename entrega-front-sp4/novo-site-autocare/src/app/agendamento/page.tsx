"use client";

import Header from "../../components/Header/Header";
import ScrollableDatas from "../../components/ScrollableDatas/ScrollableDatas";
import styles from "./Agendamento.module.css";
import ScrollableOficinas from "../../components/ScrollableOficinas/ScrollableOficinas";
import DialogConfirmacao from "../../components/DialogConfirmacao/DialogConfirmacao";
import { useState } from "react";
import { useSessionStorage } from "../../hooks/useSessionStorage";

export default function Agendamento() {
    // Pegando o problema identificado pelo chatbot
    const problemaIdentificado = useSessionStorage("problemaIdentificado");
    const orcamento = useSessionStorage("orcamento");

    const formatarProblema = (problema: string): string | null => {
        const problemas: { [key: string]: string } = {
            'pastilhas_de_freio': 'Pastilhas de freio',
            'velas_de_ignicao': 'Velas de ignição',
            'transmissao': 'Transmissão',
            'alinhamento': 'Alinhamento',
            'superaquecimento_motor': 'Superaquecimento do motor',
            'bateria': 'Bateria',
            'cambio': 'Câmbio'
        };
        return problemas[problema] || null;
    }

    const problemaFormatado = problemaIdentificado ? formatarProblema(problemaIdentificado) : null;

    // Pegando info do agendamento
    const idOficina = useSessionStorage("idOficinaSelecionada");
    const dia = useSessionStorage("diaNumSelecionado");
    const horario = useSessionStorage("horarioSelecionado");

    const [isOpen, setIsOpen] = useState(false);

    const handleOpenDialog = () => {
        if (idOficina && dia && horario) {
            setIsOpen(true);
        }
    };

    const onClose = () => {
        setIsOpen(false);
    };

    return (
        <>
            <Header />
            <div className={styles.container}>
                <div className={styles.esquerda}>
                    <ScrollableDatas />

                    <div className={styles.boxInfo}>
                        <h1>Problema do Veículo</h1>
                        <p>{problemaFormatado}</p>
                    </div>

                    <div className={styles.boxInfo}>
                        <h1>Orçamento Aproximado</h1>
                        <p>R$ {orcamento}</p>
                    </div>

                    <button 
                        className={styles.botaoConfirmar}
                        onClick={handleOpenDialog}
                    >
                        Confirmar Agendamento
                    </button>

                    <DialogConfirmacao 
                        isOpen={isOpen} 
                        onClose={onClose} 
                        problemaVeiculo={problemaFormatado} 
                        orcamento={orcamento}
                    />
                </div>
                <hr />
                <ScrollableOficinas />
            </div>
        </>
    );
}
