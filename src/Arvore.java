public class Arvore<TIPO extends Comparable>{
    private Elemento<TIPO> raiz;

    public Arvore() {
        this.raiz = null;
    }

    public Elemento<TIPO> getRaiz() {
        return raiz;
    }

    public void setRaiz(Elemento<TIPO> raiz) {
        this.raiz = raiz;
    }

    public void adicionar(TIPO valor){
        Elemento<TIPO> novoElemento = new Elemento<TIPO>(valor);
        if(this.raiz == null){
            this.raiz = novoElemento;
        }else{
            Elemento<TIPO> atual = this.raiz;
            while(true){
                if(novoElemento.getValor().compareTo(atual.getValor()) == -1){
                    if (atual.getEsquerda() != null){
                        atual = atual.getEsquerda();
                    }else{
                        atual.setEsquerda(novoElemento);
                        break;
                    }
                }else{
                    if (atual.getDireita() != null){
                        atual = atual.getDireita();
                    }else{
                        atual.setDireita(novoElemento);
                        break;
                    }
                }

            }
        }

    }

    public void emOrdem(Elemento<TIPO> atual){
         if (atual != null){
            emOrdem(atual.getEsquerda());
            System.out.println(atual.getValor());
            emOrdem(atual.getDireita());
         }
    }

    public void preOrdem(Elemento<TIPO> atual){
        if (atual != null){
            System.out.println(atual.getValor());
            preOrdem(atual.getEsquerda());
            preOrdem(atual.getDireita());
        }
    }

    public void posOrdem(Elemento<TIPO> atual){
        if (atual != null){
            posOrdem(atual.getEsquerda());
            posOrdem(atual.getDireita());
            System.out.println(atual.getValor());
        }
    }

    public boolean remover(TIPO valor){
        Elemento<TIPO> atual = this.raiz;
        Elemento<TIPO> paiAtual = null;
        while (atual != null){
            if(atual.getValor().equals(valor)){
                break;
            }else if(valor.compareTo(atual.getValor()) == -1){
                paiAtual = atual;
                atual = atual.getEsquerda();
            }else{
                paiAtual = atual;
                atual = atual.getDireita();
            }
        }
        if (atual != null){
            //primeiro caso: elemento possui filho a direita ou dois filhos
            if (atual.getDireita() != null){
                Elemento<TIPO> subs = atual.getDireita();
                Elemento<TIPO> paiSubs = atual;
                while (subs.getEsquerda() != null){
                    paiSubs = subs;
                    subs = subs.getEsquerda();
                }
                //setar o valor procurado pelo seu valor substituto
                subs.setEsquerda(atual.getEsquerda());
                if (paiAtual != null){
                    if (atual.getValor().compareTo(paiAtual.getValor()) == -1){//atual < paiAtual
                        paiAtual.setEsquerda(subs);
                    }else{
                        paiAtual.setDireita(subs);
                    }
                }else{//se nao tem pai então é a raiz
                    this.raiz = subs;
                }
                //remover elemento substituto da sua posição e setar nulo no lugar
                if (subs.getValor().compareTo(paiSubs.getValor()) == -1){//substituto < paiSubstituto
                    paiSubs.setEsquerda(null);
                }else{
                    paiSubs.setDireita(null);
                }
            }else if(atual.getEsquerda() != null){//segundo caso: possui filho somente a esquerda
                Elemento<TIPO> subs = atual.getEsquerda();
                Elemento<TIPO> paiSubs = atual;
                while (subs.getDireita() != null){
                    paiSubs = subs;
                    subs = subs.getDireita();
                }
                //setar o valor procurado pelo seu valor substituto
                if (paiAtual != null){
                    if (atual.getValor().compareTo(paiAtual.getValor()) == -1){//atual < paiAtual
                        paiAtual.setEsquerda(subs);
                    }else{
                        paiAtual.setDireita(subs);
                    }
                }else{
                    this.raiz = subs;
                }
                //remover elemento substituto da sua posição e setar nulo no lugar
                if (subs.getValor().compareTo(paiSubs.getValor()) == -1){//substituto < paiSubstituto
                    paiSubs.setEsquerda(null);
                }else{
                    paiSubs.setDireita(null);
                }
            }else{//nao possui filhos
               if (paiAtual != null){
                   if (atual.getValor().compareTo(paiAtual.getValor()) == -1){//atual < paiAtual
                       paiAtual.setEsquerda(null);
                   }else{
                       paiAtual.setDireita(null);
                   }
               }else{
                   this.raiz = null;
               }
            }
            return true;
        }else{
            return false;
        }
    }

}
