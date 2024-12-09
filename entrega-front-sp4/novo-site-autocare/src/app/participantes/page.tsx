import Header from "@/components/Header/Header";
import styles from "./Participantes.module.css";

export default function Participantes() {
    const integrantes = [
        {
            nome: "Matheus Esteves",
            rm: "554769",
            turma: "1TDSPZ",
            github: "https://github.com/matheus-esteves10",
            foto: "/assets/img/foto-matheus.jpg",
        },
        {
            nome: "Caio Henrique",
            rm: "554600",
            turma: "1TDSPJ",
            github: "https://github.com/caiohc28",
            foto: "/assets/img/foto-caio.jpg",
        },
        {
            nome: "Gabriel Falanga",
            rm: "555061",
            turma: "1TDSPM",
            github: "https://github.com/gabrielfalanga",
            foto: "/assets/img/foto-gabriel.jpg",
        },
    ];

    return (
        <>
            <Header />
            <main className={styles.conteudo}>
                <h1 className={styles.titulo}>Página de Integrantes</h1>
                {integrantes.map((integrante, index) => (
                    <div key={index} className={styles.container}>
                        <img 
                            className={styles.foto} 
                            src={integrante.foto} 
                            alt={`Foto do ${integrante.nome}`} 
                        />
                        <div>
                            <p>{integrante.nome}</p>
                            <p>RM: {integrante.rm}</p>
                            <p>Turma: {integrante.turma}</p>
                            <a href={integrante.github} target="_blank" rel="noopener noreferrer">
                                <img 
                                    className={styles.github} 
                                    src="/assets/img/github.png" 
                                    alt="Logo do GitHub" 
                                />
                            </a>
                        </div>
                    </div>
                ))}
                <a 
                    id={styles.linkRepo} 
                    href="https://github.com/gabrielfalanga/site-autocare" 
                    target="_blank" 
                    rel="noopener noreferrer"
                >
                    <div className={styles.container} id={styles.containerLink}>
                        <p>Repositório no GitHub</p>
                    </div>
                </a>
            </main>
        </>
    );
}
