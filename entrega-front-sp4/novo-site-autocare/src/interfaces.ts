export interface MensagemProps {
    texto: string;
    isResponse: boolean;
}

export interface CardOficinaProps {
    id: number;
    nome: string;
    endereco: {
        cep: string;
        bairro: string;
        rua: string;
        numero: string;
        complemento?: string;
    }
    telefone: string;
    horarios: [];
    urlImagem: string;
    horarioSelecionado?: { oficinaId: number | null; horario: string | null };
    onSelectHorario: (oficinaId: number, horario: string) => void;
}

export interface BotaoHorarioProps {
    horario: string;
    isSelected: boolean;
    onClick: () => void;
}

export interface DataAgendProps {
    diaNumero: string;
    mes: string;
    diaSemana: string;
    isSelected: boolean;
    onClick: (diaNumero: string, mes: string, diaSemana: string) => void;
}

export interface DialogConfirmacaoProps {
    isOpen: boolean; 
    onClose: () => void;
    problemaVeiculo: string | null;
    orcamento: string | null;
}