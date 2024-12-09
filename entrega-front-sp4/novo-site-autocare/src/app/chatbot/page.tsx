"use client";

import { useEffect, useRef, useState } from 'react';
import Header from "../../components/Header/Header";
import Mensagem from "../../components/Mensagem/Mensagem";
import { MensagemProps } from '@/interfaces';
import styles from "./Chatbot.module.css";

export default function Chatbot() {
  // Estados do componente
  const [chatIniciado, setChatIniciado] = useState(false);
  const [problemaIdentificado, setProblemaIdentificado] = useState(false);
  const [inputValue, setInputValue] = useState('');
  const [mensagens, setMensagens] = useState<MensagemProps[]>([]);
  const [inputDesabilitado, setInputDesabilitado] = useState(false);
  const [mostrarBotoes, setMostrarBotoes] = useState(false);

  const divMensagensRef = useRef<HTMLDivElement>(null);

  // Função para atualizar o valor do input
  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setInputValue(event.target.value);
  };

  // Função para enviar a mensagem
  const handleSend = async () => {
    const input = inputValue.trim();
    if (!input) return;

    if (!chatIniciado) setChatIniciado(true);
    
    adicionarMensagem(input, false);
    setInputValue('');

    if (!problemaIdentificado) {
      await classificarProblema(input);
    }
  };

  // Função para adicionar uma nova mensagem
  const adicionarMensagem = (texto: string, isResponse: boolean) => {
    setMensagens(prevMensagens => [...prevMensagens, { texto, isResponse }]);
  };

  // Função para classificar o problema enviado para a API
  const classificarProblema = async (input: string) => {
    const backendUrl = "http://127.0.0.1:5000/classificar";
    try {
      const response = await fetch(backendUrl, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ input }),
      });
      const data = await response.json();
      adicionarMensagem(data.response, true);

      if (data.tipo !== 'nao_identificado') {
        sessionStorage.setItem("problemaIdentificado", data.tipo);
        setProblemaIdentificado(true);
        sessionStorage.setItem("orcamento", data.orcamento);
        adicionarMensagem(data.servico, true);
        adicionarMensagem('Gostaria de agendar?', true);
        setInputDesabilitado(true);
        setMostrarBotoes(true);
      } else {
        adicionarMensagem("Desculpe, não consegui identificar o problema.", true);
      }
    } catch (error) {
      console.error('Erro:', error);
      adicionarMensagem("Ocorreu um erro ao se comunicar com o servidor. Tente novamente.", true);
    }
  };

  // Função para enviar a mensagem ao pressionar Enter
  const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === 'Enter') handleSend();
  };

  // useEffect para rolar automaticamente para o final quando as mensagens forem atualizadas
  useEffect(() => {
    if (divMensagensRef.current) {
      divMensagensRef.current.scrollTop = divMensagensRef.current.scrollHeight;
    }
  }, [mensagens]);

  // Função para lidar com o clique no botão "Sim"
  const handleSimClick = () => {
    adicionarMensagem('Sim, por favor.', false);
    adicionarMensagem('Legal! Vou te redirecionar para a página de agendamento.', true);
    setMostrarBotoes(false);
    
    // Usando window.location.href para redirecionar
    setTimeout(() => {
      window.location.href = '/agendamento';
    }, 3000);
  };

  // Função para lidar com o clique no botão "Não"
  const handleNaoClick = () => {
    setMostrarBotoes(false);
    adicionarMensagem('Não, obrigado.', false);
    adicionarMensagem('Sem problemas! Estou sempre à disposição.', true);
    adicionarMensagem('Reiniciando chat...', true);
    setProblemaIdentificado(false);
    sessionStorage.removeItem("problemaIdentificado"); // Limpa o problema identificado
    sessionStorage.removeItem("orcamento"); // Limpa o orçamento
    setTimeout(() => {
      setChatIniciado(false);
      setMensagens([]);
      setInputDesabilitado(false);
    }, 3000);
  };

  return (
    <>
      <Header />
      <div className={styles.divPai}>
        <div className={styles.container}>
          {!chatIniciado && (
            <div className={styles.inicioChat}>
              <h1>Auto<b>Care</b></h1>
              <img src="/assets/img/robo-autocare.png" alt="Imagem de carro robô" />
            </div>
          )}
          <div ref={divMensagensRef} className={chatIniciado ? styles.divMensagens : styles.displayNone}>
            {mensagens.map((mensagem, index) => (
              <Mensagem key={index} texto={mensagem.texto} isResponse={mensagem.isResponse} />
            ))}
            {mostrarBotoes && (
              <div className={styles.divBotoes}>
                <button onClick={handleSimClick}>Sim</button>
                <button onClick={handleNaoClick}>Não</button>
              </div>
            )}
          </div>
          <div className={styles.divInput}>
            <input
              type="text"
              value={inputValue}
              onChange={handleInputChange}
              onKeyDown={handleKeyDown}
              disabled={inputDesabilitado}
              placeholder="Descreva a situação..."
            />
            <button onClick={handleSend} disabled={inputDesabilitado}>
              <img src="/assets/img/submit.png" alt="Botão enviar" />
            </button>
          </div>
        </div>
      </div>
    </>
  );
}
