import Header from "@/components/Header/Header";
import styles from "./page.module.css";
import Link from "next/link";

export default function Home() {
    return (
        <>
            <Header />
            <main className={styles.conteudo}>
                <h1 className={styles.titulo}>
                    O que o <strong>AutoCare</strong> faz?
                </h1>

                <section className={styles.flexSection}>
                    <div className={styles.boxes}>
                        <div className={`${styles.box} ${styles.box1}`}>
                            <p>Identificação do problema do seu veículo</p>
                        </div>
                        <div className={`${styles.box} ${styles.box2}`}>
                            <p>Orçamento aproximado de peças e serviços</p>
                        </div>
                        <div className={`${styles.box} ${styles.box3}`}>
                            <p>Agendamento com oficinas próximas de você</p>
                        </div>
                    </div>
                    <Link href="/login">
                        <div className={styles.boxBotao}>
                            <p>Experimente o Chatbot</p>
                        </div>
                    </Link>
                </section>
            </main>
        </>
    );
}
