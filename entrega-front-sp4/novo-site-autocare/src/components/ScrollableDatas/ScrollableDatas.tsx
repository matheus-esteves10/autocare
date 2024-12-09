import styles from "./ScrollableDatas.module.css";
import DataAgendamento from "../DataAgendamento/DataAgendamento";
import { useState } from "react";

// Interface para definir o tipo das datas
interface Data {
    diaNumero: string;
    mes: string;
    diaSemana: string;
}

export default function ScrollableDatas() {
    const [dataSelecionada, setDataSelecionada] = useState<string | null>(null);
    const [indice, setIndice] = useState(0);

    const datas: Data[] = [
        { diaNumero: "26", mes: "OUT", diaSemana: "Sábado" },
        { diaNumero: "27", mes: "OUT", diaSemana: "Domingo" },
        { diaNumero: "28", mes: "OUT", diaSemana: "Segunda-feira" },
        { diaNumero: "29", mes: "OUT", diaSemana: "Terça-feira" },
        { diaNumero: "30", mes: "OUT", diaSemana: "Quarta-feira" },
        { diaNumero: "31", mes: "OUT", diaSemana: "Quinta-feira" },
        { diaNumero: "01", mes: "NOV", diaSemana: "Sexta-feira" },
        { diaNumero: "02", mes: "NOV", diaSemana: "Sábado" },
        { diaNumero: "03", mes: "NOV", diaSemana: "Domingo" },
        { diaNumero: "04", mes: "NOV", diaSemana: "Segunda-feira" },
        { diaNumero: "05", mes: "NOV", diaSemana: "Terça-feira" },
        { diaNumero: "06", mes: "NOV", diaSemana: "Quarta-feira" },
        { diaNumero: "07", mes: "NOV", diaSemana: "Quinta-feira" },
    ];

    const handleSelectData = (diaNumero: string, mes: string, diaSemana: string) => {
        setDataSelecionada(`${diaNumero}-${mes}/${diaSemana}`);
        sessionStorage.removeItem("horarioSelecionado");
        sessionStorage.setItem("diaNumSelecionado", diaNumero);
        sessionStorage.setItem("mesSelecionado", mes);
        sessionStorage.setItem("diaSemanaSelecionado", diaSemana);
    }

    const handleNext = () => {
        setIndice(prevIndice => Math.min(prevIndice + 3, datas.length - 1));
    }

    const handlePrevious = () => {
        setIndice(prevIndice => Math.max(prevIndice - 3, 0));
    }

    return (
        <div className={styles.container}>
            <h2 className={styles.selecione}>Selecione o melhor dia</h2>
            
            <div className={styles.boxSelecao}>
                <div className={styles.divBotao}>
                    <button className={`${styles.botao} ${styles.botaoBack}`} onClick={handlePrevious}>
                        <img src="/assets/img/submit.png" alt="Voltar" />
                    </button>
                </div>

                <div className={styles.divDatas}>
                    {datas.slice(indice, indice + 3).map((data, index) => (
                        <DataAgendamento 
                            key={index} 
                            diaNumero={data.diaNumero} 
                            mes={data.mes} 
                            diaSemana={data.diaSemana}
                            isSelected={dataSelecionada === `${data.diaNumero}-${data.mes}/${data.diaSemana}`}
                            onClick={handleSelectData}
                        />
                    ))}
                </div>

                <div className={styles.divBotao}>
                    <button className={styles.botao} onClick={handleNext}>
                        <img src="/assets/img/submit.png" alt="Avançar" />
                    </button>
                </div>
            </div>
        </div>
    );
}
