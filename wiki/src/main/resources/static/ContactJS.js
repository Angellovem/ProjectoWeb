(() => {
  // Definicion de maximos para el form
  const cfg = {
    nombres:     { max: 100, counter: 'nombresCount',    err: 'nombresErr' },
    apellidos:   { max: 100, counter: 'apellidosCount',  err: 'apellidosErr' },
    correo:      { max: 100, counter: 'correoCount',     err: 'correoErr' },
    descripcion: { max: 500, counter: 'descCount',       err: 'descripcionErr' },
    semestre:    {                err: 'semestreErr' } // sin contador
  };

  const emailRx = /^[A-Z0-9._-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/;

  const $ = id => document.getElementById(id);
  const setInvalid = (id, msg) => { $(id).classList.add('invalid'); $(cfg[id].err).textContent = msg || ''; };
  const clearInvalid = id => { $(id).classList.remove('invalid'); $(cfg[id].err).textContent = ''; };

  function updateCounter(id) {
    const c = cfg[id]; if (!c.max) return;
    const el = $(id);
    if (el.value.length > c.max) el.value = el.value.slice(0, c.max);
    $(c.counter).textContent = el.value.length;
  }

  function toAsciiUpper(s) {
    return s.normalize('NFD').replace(/\p{Diacritic}/gu, '').replace(/ñ/gi, 'N').toUpperCase();
  }

  // Validación en vivo de los campos 
  ['nombres','apellidos','correo','descripcion'].forEach(id => {
    const el = $(id);
    if (!el) return;
    el.addEventListener('input', () => {
      if (id === 'correo') el.value = toAsciiUpper(el.value).replace(/\s+/g, '');
      updateCounter(id);

      if (!el.value.trim()) { setInvalid(id, 'Este campo es obligatorio'); return; }
      if (id === 'correo' && !emailRx.test(el.value)) { setInvalid(id, 'Formato inválido. Ej: NOMBRE@DOMINIO.COM'); return; }
      clearInvalid(id);
    });
    updateCounter(id);
  });

  // Validación del semestre en vivo 
  const sem = $('semestre');
  if (sem) {
    sem.addEventListener('input', () => {
      const v = parseInt(sem.value, 10);
      if (Number.isNaN(v)) { setInvalid('semestre', 'Este campo es obligatorio'); return; }
      if (v < 0)  { setInvalid('semestre', 'MÍNIMO puedes estar en el SEMESTRE 0');  return; }
      if (v > 16) { setInvalid('semestre', 'MÁXIMO puedes estar en el SEMESTRE 16'); return; }
      clearInvalid('semestre');
    });
  }

  // Validación final al enviar el formulario antes de mandar todo a la DB para que no se vaya a la mierda
  const form = $('contactForm');
  if (form) {
    form.addEventListener('submit', e => {
      let ok = true;

      ['nombres','apellidos','correo','descripcion'].forEach(id => {
        const el = $(id); if (!el) return;
        const val = el.value.trim();
        if (!val) { setInvalid(id, 'Este campo es obligatorio'); ok = false; return; }
        if (id === 'correo' && !emailRx.test(val)) { setInvalid(id, 'Formato inválido. Ej: NOMBRE@DOMINIO.COM'); ok = false; }
      });

      const v = parseInt(sem.value, 10);
      if (Number.isNaN(v) || v < 0 || v > 16) {
        if (Number.isNaN(v)) setInvalid('semestre', 'Este campo es obligatorio');
        else if (v < 0)    setInvalid('semestre', 'MÍNIMO puedes estar en el SEMESTRE 0');
        else               setInvalid('semestre', 'MÁXIMO puedes estar en el SEMESTRE 16');
        ok = false;
      }

      if (!ok) e.preventDefault();
    });
  }
})();
