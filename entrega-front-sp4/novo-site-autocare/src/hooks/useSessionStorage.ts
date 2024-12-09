import { useState, useEffect } from 'react';

// Hook customizado para acessar e sincronizar com o sessionStorage
export function useSessionStorage(key: string) {
  const [value, setValue] = useState<string | null>(() => {
    if (typeof window !== 'undefined') {
      return sessionStorage.getItem(key);
    }
    return null;
  });

  useEffect(() => {
    if (typeof window === 'undefined') return; // Verifique se estamos no cliente

    const handleStorageChange = () => {
      setValue(sessionStorage.getItem(key));
    };

    window.addEventListener('storage', handleStorageChange);

    const interval = setInterval(() => {
      handleStorageChange();
    }, 1000);

    return () => {
      window.removeEventListener('storage', handleStorageChange);
      clearInterval(interval);
    };
  }, [key]);

  return value;
}
