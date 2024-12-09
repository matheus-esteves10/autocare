import { BotaoHorarioProps } from "../../interfaces";
import styles from "./BotaoHorario.module.css";

export default function BotaoHorario({ horario, isSelected, onClick }: BotaoHorarioProps) {
  
  return (
    <button className={`${styles.botaoHorario} ${isSelected ? styles.selected : ''}`} 
            onClick={onClick}>
      <p>{horario}</p>
    </button>
  );
}
