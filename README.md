# 📱 PAN Catalog — Android Architecture Showcase

Projeto Android desenvolvido com foco em **arquitetura escalável, estabilidade em produção e boas práticas exigidas em ambientes corporativos (bancários)**.

Este projeto foi criado como **preparação técnica para entrevistas Android**, aplicando conceitos reais utilizados em aplicações de grande porte, com especial atenção a **observabilidade, performance, arquitetura limpa e compatibilidade entre versões do Android**.

---

## 🎯 Objetivos do Projeto

- Demonstrar **arquitetura MVVM com boundaries bem definidas**
- Aplicar **Single-Activity + Navigation Component**
- Gerenciar estado de UI de forma previsível com **StateFlow**
- Garantir **ciclo de vida seguro** (evitar leaks e estados inválidos)
- Preparar o app para:
  - Observabilidade em produção
  - Evolução para múltiplas features
  - Modularização
  - Migração futura para Jetpack Compose

---

## 🧱 Arquitetura

### 📐 Padrão adotado
- **MVVM (Model–View–ViewModel)**
- Separação clara entre:
  - `ui` → telas, adapters, renderização
  - `domain` → modelos de domínio (imutáveis)
  - `core` → utilitários transversais (logging, estados, etc.)

### 🧭 Navegação
- **Single-Activity + Fragments**
- Navegação centralizada via **Navigation Component**
- Back stack previsível e fácil de manter

**Por que Single-Activity?**
- Facilita a escalabilidade do app
- Centraliza navegação
- Evita Activities “God Object”
- Modelo amplamente utilizado em apps corporativos

---

## 🔄 Gerenciamento de Estado

- UI baseada em **UiState selado (`sealed interface`)**
- Estados possíveis:
  - Loading
  - Success
  - Empty
  - Error

- Comunicação UI ↔ ViewModel via **StateFlow**
- Coleta lifecycle-aware usando:
  - `repeatOnLifecycle(Lifecycle.State.STARTED)`

**Benefícios:**
- Evita estados inválidos
- Facilita testes
- Reduz bugs relacionados ao ciclo de vida
- Abordagem moderna recomendada pelo Jetpack

---

## 🧵 Concorrência e Performance

- Uso de **Kotlin Coroutines**
- IO simulado de forma **não bloqueante**
- `viewModelScope` garante cancelamento automático
- RecyclerView otimizado com:
  - `ListAdapter`
  - `DiffUtil`
  - Identidade estável por `id`

---

## 🧠 Modelos de Domínio

- Modelos **imutáveis** (`data class`)
- Sem dependência de UI, banco ou rede

**Vantagens:**
- Previsibilidade
- Facilidade de teste
- Menos efeitos colaterais
- Melhor suporte a DiffUtil e cache

---

## 🧩 Estrutura de Pacotes

