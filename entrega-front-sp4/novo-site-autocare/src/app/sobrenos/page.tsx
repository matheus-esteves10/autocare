import Header from "@/components/Header/Header";
import styles from "./SobreNos.module.css";

export default function SobreNos() {
    return (
        <>
            <Header />
            <main className={styles.conteudo}>
                <section className={styles.container}>
                    <h1 className={styles.titulo}>Quem somos?</h1>
                    <p id={styles.paragrafo1}>
                        Somos a companhia que está com você em todos os momentos da vida. Ir além de uma seguradora. 
                        A Porto é um ecossistema completo que torna sonhos em realidades cotidianas. 
                        Com mais de 75 anos de experiência, a Porto abrange três principais áreas de negócios: Porto Seguro, Porto Saúde e Porto Bank, 
                        oferecendo um leque de soluções de proteção para momentos rotineiros até os mais significativos.
                    </p>
                </section>
                <section className={styles.stats}>
                    <figure className={`${styles.icon} ${styles.icon1}`}>
                        <img 
                            src="/assets/img/funcionarios.png" 
                            alt="Ícone Funcionários"
                        />
                        <figcaption>13 mil funcionários</figcaption>
                    </figure>
                    <figure className={`${styles.icon} ${styles.icon2}`}>
                        <img 
                            src="/assets/img/corretores.png" 
                            alt="Ícone Corretores Parceiros" 
                        />
                        <figcaption>+ 35 mil corretores<br />parceiros</figcaption>
                    </figure>
                    <figure className={`${styles.icon} ${styles.icon3}`}>
                        <img 
                            src="/assets/img/clientes.png" 
                            alt="Ícone Clientes" 
                        />
                        <figcaption>+ 15.8 milhões de clientes</figcaption>
                    </figure>
                    <figure className={`${styles.icon} ${styles.icon4}`}>
                        <img 
                            src="/assets/img/localizacao.png" 
                            alt="Ícone Localização"
                        />
                        <figcaption>55 sucursais escritórios<br />regionais em todo o Brasil</figcaption>
                    </figure>
                </section>
            </main>
        </>
    );
}
