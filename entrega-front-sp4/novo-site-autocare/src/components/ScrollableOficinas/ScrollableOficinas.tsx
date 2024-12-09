import styles from "./ScrollableOficinas.module.css";
import CardOficina from "../CardOficina/CardOficina";
import { useSessionStorage } from "../../hooks/useSessionStorage";
import { useState, useEffect } from "react";
import { CardOficinaProps } from "../../interfaces";

export default function ScrollableOficinas() {
    const listaOficinasString = useSessionStorage("oficinasDisponiveis");
    const [listaOficinas, setListaOficinas] = useState<CardOficinaProps[]>([]);
    const [horarioSelecionado, setHorarioSelecionado] = useState<{ oficinaId: number | null; horario: string | null }>({
        oficinaId: null,
        horario: null,
    });

    useEffect(() => {
        if (listaOficinasString) {
            try {
                const oficinas = JSON.parse(listaOficinasString);
                // Verificando se as oficinas têm as propriedades necessárias
                if (Array.isArray(oficinas) && oficinas.every(oficina => oficina.id && oficina.nome)) {
                    setListaOficinas(oficinas);
                } else {
                    setListaOficinas([]);
                }
            } catch (error) {
                console.error('Erro ao fazer o parse:', error);
                setListaOficinas([]);
            }
        } else {
            setListaOficinas([]);
        }
    }, [listaOficinasString]);

    const setSessionStorageItem = (key: string, value: string | number) => {
        sessionStorage.setItem(key, String(value));
    };

    const handleSelectHorario = (oficinaId: number, horario: string) => {
        setHorarioSelecionado({ oficinaId, horario });
        setSessionStorageItem("idOficinaSelecionada", oficinaId);
        setSessionStorageItem("horarioSelecionado", horario);

        const oficinaSelecionada = listaOficinas.find(oficina => oficina.id === oficinaId);
        if (oficinaSelecionada) {
            setSessionStorageItem("nomeOficinaSelecionada", oficinaSelecionada.nome);
        }
    };

    return (
        <div>
            <h2 className={styles.titulo}>Escolha a melhor oficina para você!</h2>
            <div className={styles.divCards}>
                {listaOficinas.length > 0 ? (
                    listaOficinas.map((oficina: CardOficinaProps) => (
                        <CardOficina
                            key={oficina.id}
                            id={oficina.id}
                            nome={oficina.nome}
                            endereco={oficina.endereco}
                            telefone={oficina.telefone}
                            horarios={oficina.horarios}
                            urlImagem={oficina.urlImagem}
                            horarioSelecionado={horarioSelecionado}
                            onSelectHorario={handleSelectHorario}
                        />
                    ))
                ) : (
                    <p className={styles.selecioneData}>Selecione uma data para ver as oficinas disponíveis.</p>
                )}
            </div>
        </div>
    );
}
