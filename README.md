# 📱 PAN Catalog — Android Architecture Showcase

Projeto Android desenvolvido com foco em **arquitetura escalável, estabilidade em produção e boas práticas utilizadas em aplicações corporativas (especialmente no setor bancário)**.

Este repositório faz parte de um **plano estruturado de preparação para entrevistas Android**, aplicando conceitos reais exigidos em vagas técnicas, como MVVM, gerenciamento de estado, ciclo de vida seguro, observabilidade e performance.

---

## 🎯 Objetivos do Projeto

- Demonstrar **arquitetura Android moderna e escalável**
- Aplicar **Single-Activity + Navigation Component**
- Gerenciar estado de UI de forma previsível com **StateFlow**
- Evitar problemas clássicos de ciclo de vida e memory leaks
- Criar uma base sólida para:
  - Observabilidade em produção
  - Evolução de features
  - Modularização
  - Migração futura para Jetpack Compose
 
## 🧱 Arquitetura

### 📐 Padrão adotado
- **MVVM (Model–View–ViewModel)**
- Separação clara de responsabilidades:
  - `ui` → Fragments, Activities, Adapters e renderização
  - `domain` → modelos de domínio (imutáveis)
  - `core` → utilitários transversais (logging, estados, helpers)

Essa separação reduz acoplamento, facilita testes e torna a base preparada para crescimento.

---

### 🧭 Navegação

- **Single-Activity + Fragments**
- Navegação centralizada via **Navigation Component**
- Back stack previsível e controlado

**Por que Single-Activity?**
- Centraliza a navegação
- Evita Activities com múltiplas responsabilidades
- Facilita deep links e modularização
- Modelo amplamente adotado em apps de grande porte

## 🔄 Gerenciamento de Estado

- UI baseada em **UiState selado (`sealed interface`)**
- Estados explícitos:
  - `Loading`
  - `Success`
  - `Empty`
  - `Error`

- Comunicação entre ViewModel e UI via **StateFlow**
- Coleta lifecycle-aware usando `repeatOnLifecycle(Lifecycle.State.STARTED)`

### Benefícios
- Estados previsíveis
- Menos bugs relacionados a lifecycle
- Código mais testável
- Melhor controle de cenários de erro

---

## 🧵 Concorrência e Performance

- Uso de **Kotlin Coroutines**
- Operações assíncronas **não bloqueantes**
- `viewModelScope` garante cancelamento automático
- Listas otimizadas com:
  - `RecyclerView`
  - `ListAdapter`
  - `DiffUtil`

## 🧠 Modelos de Domínio

- Modelos **imutáveis** (`data class`)
- Sem dependência de UI, banco ou rede

---

## 🧩 Estrutura de Pacotes

br.com.pancatalog
 ├─ ui
 │   ├─ MainActivity
 │   └─ catalog
 │       ├─ CatalogFragment
 │       ├─ CatalogViewModel
 │       ├─ CatalogUiState
 │       └─ adapter
 │           ├─ ItemAdapter
 │           └─ ItemViewHolder
 │
 ├─ domain
 │   └─ model
 │       └─ Item
 │
 └─ core
     └─ logging
         └─ AppLogger

---

## ▶️ Como Rodar o Projeto

1. Clone o repositório  
   `git clone https://github.com/seu-usuario/pan-catalog.git`

2. Abra no Android Studio  
3. Aguarde o Gradle sync  
4. Execute o app

---

## 📦 Tecnologias Utilizadas

- Kotlin 2.x
- Views + ViewBinding
- MVVM
- Kotlin Coroutines + StateFlow
- Navigation Component
- RecyclerView + DiffUtil
- Lifecycle KTX
- Material Design

---

## 🔮 Roadmap Técnico

- DetailFragment com navegação tipada
- Repository Pattern
- Offline-first (Room + Retrofit)
- Observabilidade (Crashlytics, logs, métricas)
- Feature Flags e A/B Testing
- Modularização
- CI/CD
- Migração para Jetpack Compose

---

## 👤 Autor

Luiz Camilo  
Desenvolvedor Android
