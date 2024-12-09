import styles from "./not-found.module.css"

export default function Error() {
  return (
    <div className={styles.divError}>
      <h1 className={styles.msg}>Erro 404 - Página não encontrada!</h1>
    </div>
  )
}
