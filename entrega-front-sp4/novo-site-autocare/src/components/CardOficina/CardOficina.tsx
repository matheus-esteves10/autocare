import styles from "./CardOficina.module.css";
import { CardOficinaProps } from "@/interfaces";
import BotaoHorario from "../BotaoHorario/BotaoHorario";

export default function CardOficina({
    id,
    nome,
    endereco,
    telefone,
    horarios,
    urlImagem,
    horarioSelecionado,
    onSelectHorario
}: CardOficinaProps) {
    return (
        <div className={styles.container}>
            <div className={styles.divOficina}>
                <img src={urlImagem} alt={`Imagem da oficina ${nome}`} />
                <div className={styles.infoOficina}>
                    <h2>{nome}</h2>
                    <p>
                        {endereco.rua}, {endereco.numero} {endereco.complemento} - {endereco.bairro} | {endereco.cep}
                    </p>
                    <p>Telefone: {telefone}</p>
                </div>
            </div>
            <hr className={styles.divisor} />
            <h2>Selecione o horário</h2>
            <div className={styles.divHorarios}>
                {horarios && horarios.length > 0 ? (
                    horarios.map((horario: string, index: number) => (
                        <BotaoHorario
                            key={index}
                            horario={horario}
                            isSelected={horarioSelecionado?.horario === horario && horarioSelecionado?.oficinaId === id}
                            onClick={() => onSelectHorario(id, horario)} // Chama a função ao clicar
                        />
                    ))
                ) : (
                    <p className={styles.carregandoHorarios}>Carregando horários disponíveis.</p>
                )}
            </div>
        </div>
    );
}
