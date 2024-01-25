class Calculadora{
    constructor(textoOperandoAnterior, textoOperandoAtual){
        this.textoOperandoAnterior = textoOperandoAnterior;
        this.textoOperandoAtual = textoOperandoAtual;
        this.limpar();
    }

    limpar(){
        this.operandoAtual = '';
        this.operandoAnterior = '';
        this.operacao = '';
    }

    deletar(){
        this.operandoAtual = this.operandoAtual.toString().slice(0, -1);
    }

    escolherOperacao(operacao){
        if(this.operandoAtual === '') return;
        if(this.operandoAnterior !== ''){
            this.calculo();
        }
        this.operacao = operacao;
        this.operandoAnterior = this.operandoAtual;
        this.operandoAtual = '';
    }

    adicionarNumero(numero){
        if(numero === '.' && this.operandoAtual.includes('.')) return;
        this.operandoAtual = this.operandoAtual.toString() + numero.toString();
    }

    calculo(){
        let resultado;
        const anterior = parseFloat(this.operandoAnterior);
        const atual = parseFloat(this.operandoAtual);
        if(isNaN(anterior) || isNaN(atual)) return;
        switch(this.operacao){
            case '+':
                resultado = anterior + atual;
                break;

            case '-':
                resultado = anterior - atual;
                break;

            case '÷':
                resultado = anterior / atual;
                break;

            case '*':
                resultado = anterior * atual;
                break;

            default:
                return;
        }
        this.operandoAtual = resultado;
        this.operacao = '';
        this.operandoAnterior = '';
    }

    atualizarSaida(){
        this.textoOperandoAtual.innerText = this.operandoAtual;
        this.textoOperandoAnterior.innerText = `${this.operandoAnterior} ${this.operacao}`;
    }
}

const botoesDeNumeros = document.querySelectorAll('[data-numero]');
const botoesDeOperadores = document.querySelectorAll('[data-operador');
const botaoIgual = document.querySelector('[data-igual]');
const botaoDeletar = document.querySelector('[data-deletar]');
const botaoLimpar = document.querySelector('[data-limpar]');
const textoOperandoAnterior = document.querySelector('[data-operando-anterior]');
const textoOperandoAtual = document.querySelector('[data-operando-atual]');

const calculadora = new Calculadora(textoOperandoAnterior, textoOperandoAtual);

botoesDeNumeros.forEach(button => {
    button.addEventListener('click', () => {
        calculadora.adicionarNumero(button.innerText);
        calculadora.atualizarSaida();
    });
});

botaoLimpar.addEventListener('click', button => {
    calculadora.limpar();
    calculadora.atualizarSaida();
})

botoesDeOperadores.forEach(button => {
    button.addEventListener('click', () => {
        calculadora.escolherOperacao(button.innerText);
        calculadora.atualizarSaida();
    });
});

botaoDeletar.addEventListener('click', button => {
        calculadora.deletar();
        calculadora.atualizarSaida();
});

botaoIgual.addEventListener('click', button => {
    calculadora.calculo();
    calculadora.atualizarSaida();
});