import Link from "next/link";
import Image from "next/image";
import styles from "./Header.module.css";

export default function Header() {
    return (
        <header className={styles.cabecalho}>
            <Link href="/" className={styles.logo}>
                <h1>
                    Auto<b>Care</b>
                </h1>
            </Link>

            <div className={styles.rightContent}>
                <nav>
                    <ul>
                        <li className={styles.link} id={styles.sobre}>
                            <Link href="/sobrenos">Sobre nós</Link>
                        </li>
                        <li className={styles.link} id={styles.ajuda}>
                            <Link href="/ajuda">Ajuda</Link>
                        </li>
                        <li className={styles.link}>
                            <Link href="/participantes">Participantes</Link>
                        </li>
                    </ul>
                </nav>
            </div>

            <div className={styles.user}>
                <Link href="/login">
                    <Image 
                        className={styles.imgUsuario} 
                        src="/assets/img/usuario.png" 
                        alt="Usuário" 
                        width={40} 
                        height={40} 
                    />
                </Link>
            </div>
        </header>
    );
}
