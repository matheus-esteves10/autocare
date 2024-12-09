import { DataAgendProps } from "../../interfaces";
import styles from "./DataAgendamento.module.css";

export default function DataAgendamento({
    diaNumero,
    mes,
    diaSemana,
    isSelected,
    onClick
}: DataAgendProps) {

    // Função para converter o mês abreviado em seu valor numérico correspondente
    const mesParaNumero = (mes: string): string | null => {
        const meses: { [key: string]: string } = {
            'JAN': '01',
            'FEV': '02',
            'MAR': '03',
            'ABR': '04',
            'MAI': '05',
            'JUN': '06',
            'JUL': '07',
            'AGO': '08',
            'SET': '09',
            'OUT': '10',
            'NOV': '11',
            'DEZ': '12',
        };
        return meses[mes.toUpperCase()] || null; // Converte para maiúsculas e busca
    };

    const buscarOficinasAPI = async () => {
        const backendUrl = "http://127.0.0.1:5000/buscar/oficinas"; // URL da API

        // Transformando o mês
        const mesNumero = mesParaNumero(mes);
        if (!mesNumero) {
            return; // Não prossegue se o mês for inválido
        }

        const data = `${diaNumero}-${mesNumero}-2024`; // Formata a data para enviar para a API

        try {
            const response = await fetch(backendUrl, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ data }), // Envia a data para a API
            });

            if (!response.ok) {
                throw new Error('Erro na resposta da API'); // Lança um erro se a resposta não for ok
            }

            const dados = await response.json(); // Recebe a resposta da API
            const oficinasDisponiveis = dados.oficinas; // Seleciona a lista de oficinas disponíveis
            sessionStorage.setItem("oficinasDisponiveis", JSON.stringify(oficinasDisponiveis)); // Guarda o valor na sessionStorage

        } catch (error) {
            console.error('Erro:', error); // Loga qualquer erro
        }
    };

    return (
        <button
            className={`${styles.divData} ${isSelected ? styles.selected : ''}`} 
            onClick={() => {
                onClick(diaNumero, mes, diaSemana); 
                buscarOficinasAPI();
            }}
        >
            <p className={styles.diaSemana}>{diaSemana}</p>
            <p className={styles.diaNumero}>{diaNumero}</p>
            <p className={styles.mes}>{mes}</p>
        </button>
    );
}
