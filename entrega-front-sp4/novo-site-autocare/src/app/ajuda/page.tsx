import Header from "@/components/Header/Header";
import styles from "./Ajuda.module.css";

export default function Ajuda() {
    return (
        <>
            <Header />
            <h1 className={styles.titulo}>Perguntas Frequentes</h1>
            <main className={styles.conteudo}>
                <div className={styles.container}>
                    <div className={styles.pergunta}>
                        <h2>Como funciona a identificação do problema do veículo?</h2>
                    </div>
                    <div className={styles.resposta}>
                        <p>A inteligência artificial irá localizar o seu problema baseado na sua descrição por palavras ou por meio da identificação de luzes que indicam esses problemas no painel do carro (para a segunda alternativa é necessário o envio de foto no chat com a IA).</p>
                    </div>
                </div>
                <div className={styles.container}>
                    <div className={styles.pergunta}>
                        <h2>Como é feito o orçamento?</h2>
                    </div>
                    <div className={styles.resposta}>
                        <p>O orçamento é realizado por meio de um modelo de aprendizado de máquina que, por meio de características do veículo e o problema identificado, chega a uma conclusão precisa.</p>
                    </div>
                </div>
                <div className={styles.container}>
                    <div className={styles.pergunta}>
                        <h2>Como é o sistema de agendamento com oficinas mecânicas?</h2>
                    </div>
                    <div className={styles.resposta}>
                        <p>Você informa qual é o seu endereço e encontraremos a oficina parceira disponível mais perto de você.</p>
                    </div>
                </div>
            </main>
            <footer>
                <p>Ainda tem dúvidas?<br/>Adicione a Porto no WhatsApp e siga as orientações da nossa assistente virtual: <a id={styles.fone} href="http://porto.vc/wpp29?_gl=1*12k1ulw*_ga*MTg0NjY5MDMzNS4xNzEyMDYyODM5*_ga_S9FTJ8D526*MTcxMjgzODgwOS42LjEuMTcxMjgzODgyMC40OS4wLjA." target="_blank">11 3003 9303</a></p>
            </footer>
        </>
    );
}
