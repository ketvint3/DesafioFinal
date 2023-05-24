package com.Desafio.Final.Diamond.repositories.criteria.impl;

import com.Desafio.Final.Diamond.models.ViagemModel;
import com.Desafio.Final.Diamond.models.enu.ViagemEnum;
import com.Desafio.Final.Diamond.repositories.criteria.ViagemRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ViagemRepositoryCustomImpl implements ViagemRepositoryCustom {
    private EntityManager entityManager;
    public ViagemRepositoryCustomImpl(EntityManager manager) {

        this.entityManager = manager;
    }
    @Override
    public List<ViagemModel> listarPendentes() {

        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<ViagemModel> query = criteriaBuilder.createQuery(ViagemModel.class);

        Root<ViagemModel> viagem = query.from(ViagemModel.class);

        if (viagem != null) {
            query.select(viagem).where(criteriaBuilder.equal(viagem.get("statusViagem"), ViagemEnum.PENDENTE));
        }

        TypedQuery<ViagemModel> typedResult = this.entityManager.createQuery(query);
        return typedResult.getResultList();
    }
}
